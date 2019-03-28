import {Button, DialogContainer, TextField} from "react-md";
import React, {PureComponent} from "react";

export default class NewBlogPost extends PureComponent {
    constructor(props) {
        super(props);

        this.titleTextField = React.createRef();
        this.authorTextField = React.createRef();
        this.contentTextField = React.createRef();

        this.state = {visible: false};
    }

    show = () => {
        this.setState({visible: true});
        console.log("NewPostDialog: show()");
    }

    hide = () => {
        this.setState({visible: false});
    }

    postIt = () => {
        this.hide();
        let newPost = {
            "title": this.titleTextField.current.value,
            "author": this.authorTextField.current.value,
            "content": this.contentTextField.current.value,
            "time": new Date().getTime()
        }

        fetch('./post', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newPost)
        });
        window.location.reload();
    }

    render() {
        const actions = [];
        actions.push(<Button flat secondary swapTheming onClick={this.hide}>Cancel</Button>);
        actions.push(<Button flat primary swapTheming onClick={this.postIt}>Send</Button>);

        return (
            <div>
                <Button floating primary onClick={this.show}>add</Button>
                <DialogContainer
                    id="new-post"
                    visible={this.state.visible}
                    onHide={this.hide}
                    actions={actions}
                    title="Write a new Post"
                    width={600}
                >
                    <TextField
                        id="title"
                        label="Title"
                        required={true}
                        ref={this.titleField}
                    />
                    <TextField
                        id="author"
                        label={"Author"}
                        required={true}
                        ref={this.authorField}
                    />
                    <TextField
                        id="content"
                        label="Content"
                        rows={5}
                        required={true}
                        ref={this.contentField}
                    />
                </DialogContainer>
            </div>
        );
    }
}