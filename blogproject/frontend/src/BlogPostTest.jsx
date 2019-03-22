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

    render() {
        const style = {
            margin: 50,
            minWidth: 320,
        }
        return (
            <div>
                <Card style={style} classname="blogpost">
                    <CardTitle title={this.props.title} subtitle={"By: " + this.props.author}/>
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