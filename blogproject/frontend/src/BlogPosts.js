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

    render() {
        return (
            this.state.blogpostdata.map((item) =>
                <div key={item.id}>
                    <BlogPostTest
                        id={item.id}
                        title={item.title}
                        author={item.author}
                        content={item.content}
                    />
                </div>
            )
        );
    }
}