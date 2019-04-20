import {Card, CardText, CardTitle, CardActions, Button, TextField} from "react-md";
import React, {PureComponent} from "react";
import Comment from './Comment.jsx';

import './App.scss';

export default class BlogPostTest extends PureComponent {
    constructor(props) {
        super(props);

        this.commentTextField = React.createRef();
        this.nicknameTextField = React.createRef();

    }

    comment = () => {
        let url = "./addComment"
        let postedComment = {
            "blogId": this.props.id,
            "author": this.nicknameTextField.current.value,
            "content": this.commentTextField.current.value,
        };

        fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(postedComment)
        }).then(response => response.json())
        .then(json => {
            let newDataTable = this.props.commentData.slice();
            newDataTable.push(json);
            this.props.updateCommentData(newDataTable);
        });
    }

    deletePost = () =>{
        let url = "./deleteBlogItem/" + this.props.id;
        let deletedPost = {
            "id": this.props.id,
            "title": this.props.title,
            "author": this.props.author,
            "content": this.props.content,
            "creationDate": this.props.creationDate
        };

        fetch(url, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(deletedPost)
        }).then(response => response.json()).then(json => console.log(json));
        let newDataTable = this.props.data.slice();
        for( var i = 0; i < newDataTable.length; i++){
           if (newDataTable[i].id === this.props.id) {
             newDataTable.splice(i, 1);
           }
        }
        this.props.updateData(newDataTable);
    }

    makeDate = () => {
        var currentDate = new Date(this.props.creationDate);

        var date = currentDate.getDate();
        var month = currentDate.getMonth(); //Be careful! January is 0 not 1
        var year = currentDate.getFullYear();

        var dateString = date + "." +(month + 1) + "." + year;

        return (
            dateString
        )
    }

    showComments = () => {
        const style = {
            marginTop: 5,
            marginBottom: 5,
            maxWidth: 320,
        }

        return (
          <div>
            {this.props.commentData.map((comment) => {
              if (this.props.id === comment.blogId) {
                return (
                    <div key={comment.id}>
                        <Comment
                            id={comment.id}
                            blogId={comment.blogId}
                            commentDate={comment.commentDate}
                            author={comment.author}
                            content={comment.content}
                            likes={comment.likes}
                            commentData={this.props.commentData}
                            updateCommentData={this.props.updateCommentData}
                        />
                    </div>
                );
              }
              return null;
            })}
          </div>
        );
    }

    render() {
        const style = {
            margin: 50,
            minWidth: 320,
        }
        return (
            <div>
                <Card style={style} classname="blogpost">
                    <CardTitle
                     title={this.props.title}
                     subtitle={"By: " + this.props.author + ", " + this.makeDate()}
                    />
                    <CardText>
                        <p>{this.props.content}</p>

                    </CardText>
                    <CardActions expander>
                    </CardActions>
                    <CardText expandable>
                        <TextField
                              id={"postCommentField" + this.props.id}
                              required
                              label="Comment"
                              lineDirection="center"
                              className="md-cell md-cell--bottom"
                              ref={this.commentTextField}
                        />
                        <TextField
                              id={"commentAuthorField" + this.props.id}
                              required
                              label="Nickname"
                              lineDirection="center"
                              className="md-cell md-cell--bottom"
                              ref={this.nicknameTextField}
                        />
                        <Button raised onClick={this.comment}>Comment</Button>
                        <Button raised onClick={this.deletePost}>Delete</Button>
                        {this.showComments()}
                    </CardText>
                </Card>
            </div>
        )
    }
}