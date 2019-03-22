import React, { Component } from 'react';
import Comment from './Comment.jsx';


export default class Comments extends Component {
    constructor(props) {
        super(props);
        this.state = {
            commentData: [
            {
                id: 0,
                postId: 0,
                author: "Laboryyser",
                content: "This is a commment"
            }
            ]
        }
    }

    componentDidMount() {
        console.log("Comments: componentDidMount()");
        /* fetch('/getComments')
                    .then(response => response.json())
                    .then(data => this.setState({commentData: data})); */
    }

    render() {
        return (
            this.state.commentData.map((comment) =>
                <div key={comment.id}>
                    <Comment
                        id={comment.id}
                        author={comment.author}
                        content={comment.content}
                    />
                </div>
            )
        )
    }
}