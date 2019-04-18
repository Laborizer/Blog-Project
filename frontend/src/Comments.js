import React, { Component } from 'react';
import Comment from './Comment.jsx';


export default class Comments extends Component {
    constructor(props) {
        super(props);
        this.state = {
            commentData: [
            ]
        }
    }

    componentDidMount() {
        console.log("Comments: componentDidMount()");
        let url = '/getComments/' + this.props.postId;
        fetch(url)
            .then(response => response.json())
            .then(data => this.setState({commentData: data}));
    }

    render() {
        return (
            this.state.commentData.map((comment) =>
                <div key={comment.id}>
                    <Comment
                        id={comment.id}
                        blogId={comment.blogId}
                        commentDate={comment.commentDate}
                        author={comment.author}
                        content={comment.content}
                        likes={comment.likes}
                    />
                </div>
            )
        )
    }
}