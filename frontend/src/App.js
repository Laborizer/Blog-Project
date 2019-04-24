import React, { Component } from 'react';
import {Toolbar} from 'react-md';
import './App.scss';

import NewPostDialog from './NewPostDialog.jsx';
import BlogPostTest from './BlogPostTest.jsx';
import Search from './Search.jsx';

/**
 * Blog-Pro is a simple blogging application. It allows users to create blog posts with tags as well as browse, search
 * comment other blog posts.
 *
 * App component is the main component of the React application. It holds and controls other components of the
 * app and user interface.
 *
 * @author Lauri Laiho
 * @since 2019-03-14
 */
class App extends Component {

    /**
     * Constructor of App component
     */
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

    /**
     * Function updates the blog post data of the state
     *
     * @param newData New data to be set into the state. Contains blog post objects.
     */
    updateData = (newData) => {
        this.setState({loadingData: true});
        this.setState({data: newData, loadingData: false})
    }

    /**
     * Function updates the comment data of the state
     *
     * @param newData New data to be set into the state. Contains comment objects.
     */
    updateCommentData = (newData) => {
        this.setState({commentData: newData})
    }

    /**
     * Function updates the tag data of the state
     *
     * @param newData New data to be set into the state. Contains tag objects.
     */
    updateTagData = (newData) => {
        this.setState({tagData: newData})
        console.log(this.state.tagData);
    }

    /**
     * Lifecycle method invoke when component has been mounted by React. Fetches data from the backend and sets them
     * into state.
     */
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
                    <BlogPostTest key={item.id}
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
            )
        );
    }

    getPostsByTags = (tag) => {
        let hitArray = []
        for(let item of this.state.data) {
            if (item.id === tag.blogId) {
                hitArray.push(item);
            }
        }

        return hitArray;

    }

    onAutocomplete = (hit) => {
        let newDataTable = [];
        for(let tag of this.state.tagData) {
            if (tag.tagName === hit) {
                let hits = this.getPostsByTags(tag);
                for (let i=0; i<hits.length;i++) {
                    newDataTable.push(hits[i]);
                }
            }
        }
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
            marginLeft: 5,
            marginRight: 5
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
                    <NewPostDialog
                        data={this.state.data}
                        tagData={this.state.tagData}
                        updateData={this.updateData}
                        updateTagData={this.updateTagData}
                    />
                    <div style={blogPostsStyle}>
                        {this.showData(this.state.searchResults)}
                    </div>

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
                    <NewPostDialog
                        data={this.state.data}
                        tagData={this.state.tagData}
                        updateData={this.updateData}
                        updateTagData={this.updateTagData}
                    />
                    <div style={blogPostsStyle}>
                        {this.showData(this.state.data)}
                    </div>

                </div>
            );
        }

    }
}

export default App;
