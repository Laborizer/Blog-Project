import {Card, CardText, CardTitle, CardActions, Button, TextField} from "react-md";
import React, {PureComponent} from "react";
import Comments from './Comments.js';

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
        }).then(response => response.json()).then(json => console.log(json));
        setTimeout(function(){
            window.location.reload()
        }, 250);
    }

    delete = () => {
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
        setTimeout(function(){
            window.location.reload()
        }, 250);
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
                        {<Comments postId={this.props.id}/>}
                        <Button raised onClick={this.delete}>Delete</Button>
                    </CardText>
                </Card>
            </div>
        )
    }
}