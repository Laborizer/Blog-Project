import {Card, CardText, CardTitle, CardActions, Button} from "react-md";
import React, {PureComponent} from "react";

export default class BlogPostTest extends PureComponent {
    render() {
        return (
            <Card className="md-block-centered">
                <CardTitle title={this.props.title} subtitle={"By: " + this.props.author}/>
                <CardText>
                    <p>{this.props.content}</p>
                </CardText>
            </Card>
        )
    }
}