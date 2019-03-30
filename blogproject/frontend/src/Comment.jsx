import {Card, CardText, CardTitle, CardActions, Button} from "react-md";
import React, {PureComponent} from "react";

import './App.scss';

export default class Comment extends PureComponent {

    like = () => {
        console.log("Liked: " + this.props.id);
    }

    render() {
        const style = {
            marginTop: 5,
            marginBottom: 5,
            maxWidth: 320,
        }
        return (
            <div>
                <Card style={style} classname="comment">
                    <CardTitle title={this.props.title} subtitle={"By: " + this.props.author}/>
                    <CardText>
                        <p>{this.props.content}</p>
                    </CardText>
                    <CardActions>
                        <Button style={{align: 'left'}} raised onClick={this.like}>Like</Button>
                    </CardActions>
                </Card>
            </div>
        )
    }
}