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
import Login from './Login.jsx';

const styles = {
  content: { minHeight: 'auto' },
};

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            commentData: [],
            searchResults: [],
            tagData: [],
            loadingData: false,
            showSearch: false
        }

    }

    updateData = (newData) => {
        this.setState({loadingData: true});
        this.setState({data: newData, loadingData: false})
    }

    updateCommentData = (newData) => {
        this.setState({commentData: newData})
    }

    updateTagData = (newData) => {
        this.setState({tagData: newData})
        console.log(this.state.tagData);
    }

    componentDidMount() {
        this.setState({loadingData: true});

        fetch('/getBlogItems')
            .then(response => response.json())
            .then(json => this.setState({data: json}))
        fetch('/getAllComments')
            .then(response => response.json())
            .then(json => this.setState({commentData: json}))
        fetch('/getAllTags')
            .then(response => response.json())
            .then(json => this.setState({tagData: json, loadingData: false}))

    }

    showData = (givenData) => {
        return (
            givenData.map((item) =>
                <div key={item.id}>
                    <BlogPostTest
                        id={item.id}
                        title={item.title}
                        author={item.author}
                        content={item.content}
                        creationDate={item.creationDate}
                        data={this.state.data}
                        commentData={this.state.commentData}
                        tagData={this.state.tagData}
                        updateData={this.updateData}
                        updateCommentData={this.updateCommentData}
                        updateTagData={this.updateTagData}
                    />
                </div>
            )
        );
    }

    onAutocomplete = (hit) => {
        let newDataTable = [];
        for(let item of this.state.data) {
            if (item.title === hit) {
                newDataTable.push(item);
            }
        }
        this.setState({
            searchResults: newDataTable,
            showSearch: true
        })
    }

    onChange = (item) => {
        if(item === '') {
            this.setState({
                searchResults: [],
                showSearch: false
            })
        }
    }


    render() {
        const blogPostsStyle = {
            marginLeft: 300,
            marginRight: 300
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
                        />
                    <div>
                        <p>Loading...</p>
                    </div>
                </div>
            );
        }

        if (!this.state.loadingData && this.state.showSearch) {
            return (
                <div className="BlogApp">
                    <Toolbar
                          colored
                          title="Blog-Pro"
                          children={<Search
                                        data={this.state.data}
                                        tagData={this.state.tagData}
                                        onAutocomplete={this.onAutocomplete}
                                        onChange={this.onChange}
                          />}
                        />
                    <div style={blogPostsStyle}>
                        {this.showData(this.state.searchResults)}
                    </div>
                    <NewPostDialog
                        data={this.state.data}
                        tagData={this.state.tagData}
                        updateData={this.updateData}
                        updateTagData={this.updateTagData}
                    />
                </div>
            );
        } else if (!this.state.loadingData) {
            return (
                <div className="BlogApp">
                    <Toolbar
                          colored
                          title="Blog-Pro"
                          children={<Search
                                        data={this.state.data}
                                        tagData={this.state.tagData}
                                        onAutocomplete={this.onAutocomplete}
                                        onChange={this.onChange}
                          />}
                        />
                    <div style={blogPostsStyle}>
                        {this.showData(this.state.data)}
                    </div>
                    <NewPostDialog
                        data={this.state.data}
                        tagData={this.state.tagData}
                        updateData={this.updateData}
                        updateTagData={this.updateTagData}
                    />
                </div>
            );
        }

    }
}

export default App;
