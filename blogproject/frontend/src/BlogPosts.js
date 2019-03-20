import React, { Component } from 'react';
import BlogPostTest from './BlogPostTest.jsx';


export default class BlogPosts extends Component {
    constructor(props) {
        super(props);
        this.state = {
            blogpostdata: [
            ]
        }
    }

    componentDidMount() {
        console.log("componentDidMount()");
        fetch('/getBlogItems')
            .then(response => response.json())
            .then(data => this.setState({blogpostdata: data}));
    }

/*
    getBlogData = () => {
        fetch('/blogposts')
            .then(response => response.json())
            .then(data => this.setState({data: data.content}));
    } */

    render() {
        return (
            this.state.blogpostdata.map((item) =>
                <div key={item.id}>
                    <BlogPostTest
                        title={item.title}
                        author={item.author}
                        content={item.content}
                    />
                </div>
            )
        );
    }
}