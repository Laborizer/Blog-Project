import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Button,
    Toolbar
} from 'react-md';
import './App.scss';

import NewPostDialog from './NewPostDialog.jsx';
import BlogPostTest from './BlogPostTest.jsx';
import Search from './Search.jsx';

const styles = {
  content: { minHeight: 'auto' },
};

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            commentData: [],
            loadingData: false
        }

    }

    updateData = (newData) => {
        this.setState({loadingData: true});
        this.setState({data: newData, loadingData: false})
    }

    updateCommentData = (newData) => {
        this.setState({commentData: newData})
    }

    componentDidMount() {
        this.setState({loadingData: true});

        fetch('/getBlogItems')
            .then(response => response.json())
            .then(json => this.setState({data: json}))
        fetch('/getAllComments')
            .then(response => response.json())
            .then(json => this.setState({commentData: json, loadingData: false}))

    }

    showData = () => {
        return (
            this.state.data.map((item) =>
                <div key={item.id}>
                    <BlogPostTest
                        id={item.id}
                        title={item.title}
                        author={item.author}
                        content={item.content}
                        creationDate={item.creationDate}
                        data={this.state.data}
                        commentData={this.state.commentData}
                        updateData={this.updateData}
                        updateCommentData={this.updateCommentData}
                    />
                </div>
            )
        );
    }

    render() {
        const style = {
        };

        const postButtonStyle = {
            margin: 10,
            minWidth: 320,
            float: 'middle'
        };

        if (this.state.loadingData) {
            return (
                <div className="BlogApp">
                    <Toolbar
                          colored
                          title="Blog-Pro"
                          children={<Search/>}
                        />
                    <div>
                        <p>Loading...</p>
                    </div>
                </div>
            );
        }

        if (!this.state.loadingData) {
            return (
                <div className="BlogApp">
                    <Toolbar
                          colored
                          title="Blog-Pro"
                          children={<Search/>}
                        />
                    <div>
                        {this.showData()}
                    </div>
                    <NewPostDialog
                        data={this.state.data}
                        updateData={this.updateData}
                    />
                </div>
            );
        }

    }
}

export default App;
