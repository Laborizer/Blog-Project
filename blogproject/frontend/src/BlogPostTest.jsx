import {Card, CardText, CardTitle, CardActions, Button, TextField} from "react-md";
import React, {PureComponent} from "react";
import Comments from './Comments.js';

import './App.scss';

export default class BlogPostTest extends PureComponent {
    constructor(props) {
        super(props);
    }

    comment = () => {
        console.log("Commented on: " + this.props.id);
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
                              label="Comment"
                              lineDirection="center"
                              className="md-cell md-cell--bottom"
                        />
                        <TextField
                              id={"commentAuthorField" + this.props.id}
                              label="Nickname"
                              lineDirection="center"
                              className="md-cell md-cell--bottom"
                        />
                        <Button raised onClick={this.comment}>Comment</Button>
                        {<Comments postId={this.props.id}
                        />}
                    </CardText>
                </Card>
            </div>
        )
    }
}