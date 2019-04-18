import {Card, CardText, CardTitle, CardActions, Button} from "react-md";
import React, {PureComponent} from "react";

import './App.scss';

export default class Comment extends PureComponent {
    constructor(props) {
            super(props);
    }

    like = () => {
        console.log("Liked: " + this.props.id + " On post: " + this.props.blogId);
        let url = "/likeComment"
        /*
        let likedComment = {
            "id": this.props.id
        }
        */
        let likedComment = {
            "id": this.props.id,
            "blogId": this.props.blogId,
            "commentDate": this.props.commentDate,
            "author": this.props.author,
            "content": this.props.content,
            "likes": this.props.likes
        }
        console.log(likedComment);
        fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(likedComment)
        }).then(response => response.json()).then(json => console.log(json));
        setTimeout(function(){
            window.location.reload()
        }, 250);
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
                        <Button style={{align: 'left', marginRight: 10}} raised onClick={this.like}>Like</Button>
                        <p>Likes: {this.props.likes}</p>
                    </CardActions>
                </Card>
            </div>
        )
    }
}