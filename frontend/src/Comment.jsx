import {Card, CardText, CardTitle, CardActions, Button} from "react-md";
import React, {PureComponent} from "react";

import './App.scss';

export default class Comment extends PureComponent {
    constructor(props) {
        super(props);
        this.state = {
            isLiked: false
        }

    }

    like = () => {
        if (!this.state.isLiked) {
            let url = "/likeComment/" + this.props.id

            let likedComment = {
                "id": this.props.id
            }

            fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(likedComment)
            }).then(response => response.json())
            .then(json => {
                this.setState({isLiked: true});
                let newDataTable = this.props.commentData.slice();
                for( var i = 0; i < newDataTable.length; i++){
                    if (newDataTable[i].id === this.props.id) {
                        newDataTable[i].likes += 1;
                    }
                }
                this.props.updateCommentData(newDataTable);
            });
        }
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