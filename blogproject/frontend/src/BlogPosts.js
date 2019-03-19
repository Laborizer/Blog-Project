import React, { Component } from 'react';
import BlogPostTest from './BlogPostTest.jsx';


export default class BlogPosts extends Component {
    constructor(props) {
        super(props);
        this.state = {
            blogpostdata: [
                {
                    id: 1,
                    title: "Test",
                    author: "Lauri Laiho",
                    content: "Is content"
                },
                {
                    id: 2,
                    title: "Test",
                    author: "Lauri Laiho",
                    content: "Hello World!"
                }
            ]
        }
    }

    componentDidMount() {
        console.log("componentDidMount()");
        fetch('/getBlogItems')
            .then(response => response.json())
            .then(data => this.setState({blogpostdata: data.content}));
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