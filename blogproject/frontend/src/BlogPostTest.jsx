import {Card, CardText, CardTitle, CardActions, Button} from "react-md";
import React, {PureComponent} from "react";

export default class BlogPostTest extends PureComponent {
    render() {
        const style = {
            margin: 20,
            minWidth: 320,
            maxWidth: 1080
        }
        return (
            <Card style={style} className="md-block-centered">
                <CardTitle title={this.props.title} subtitle={"By: " + this.props.author}/>
                <CardText>
                    <p>{this.props.content}</p>

                </CardText>
            </Card>
        )
    }
}