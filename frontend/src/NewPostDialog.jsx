import {Button, DialogContainer, TextField, Autocomplete, Chip} from "react-md";
import React, {PureComponent} from "react";

export default class NewBlogPost extends PureComponent {
    constructor(props) {
        super(props);

        this.titleTextField = React.createRef();
        this.authorTextField = React.createRef();
        this.contentTextField = React.createRef();
        this.tagsTextField = React.createRef();

        this.state = {
            visible: false,
            tags: []
        };
    }

    show = () => {
        this.setState({visible: true});
        console.log("NewPostDialog: show()");
    }

    hide = () => {
        this.setState({visible: false});
    }

/*
    formatDate = () => {
        var d = new Date(new Date().getTime()),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return (
            [year, month, day].join('-')
        )
    } */

    postIt = () => {
        let newPost = {
            "title": this.titleTextField.current.value,
            "author": this.authorTextField.current.value,
            "content": this.contentTextField.current.value,
            "creationDate": new Date().getTime()
        }
        let blogItem = {};
        console.log(newPost)

        fetch('./addBlogItem', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newPost)
        }).then(response => response.json())
        .then(json => {
            let newDataTable = this.props.data.slice();
            blogItem = json;
            newDataTable.push(json);
            this.props.updateData(newDataTable);
            this.postTags(blogItem);
        });
    }

    postTags = (blogItem) => {
        this.hide();
        let tagArray = []
        console.log(tagArray)
        for (let i=0; i<this.state.tags.length;i++) {
            let tagObject = {};
            tagObject.blogId = blogItem.id;
            tagObject.tagName = this.state.tags[i].tagName
            tagArray.push(tagObject)
            console.log(this.state.tags[i])
        }
        fetch('./addTags', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(tagArray)
        }).then(response => response.json())
        .then(json => {
            let newDataTable = [];
            for (let i=0;i<json.length;i++) {
                let newTag = {}
                newTag.id = json[i].id;
                newTag.blogId = json[i].blogId;
                newTag.tagName = json[i].tagName;
                newDataTable.push(newTag);
            }
            this.props.updateTagData(newDataTable);
            this.setState({tags: []});
        });
    }

    addTag = () => {
        const addedTags = this.state.tags.slice();
        let tag = {
            tagName: "#" + this.tagsTextField.current.value
        }
        addedTags.push(tag);
        //this.props.updateTagData(addedTags);
        this.setState({tags: addedTags})
        console.log("Added tag");
    }

    removeTag = (tag) => {
        const addedTags = this.state.tags.slice();
        for( var i = 0; i < addedTags.length; i++){
            if (addedTags[i].tagName === tag.tagName) {
                addedTags.splice(i, 1);
            }
        }
        this.setState({tags: addedTags});
    }

    render() {
        const actions = [];
        actions.push(<Button flat secondary swapTheming onClick={this.hide}>Cancel</Button>);
        actions.push(<Button flat primary swapTheming onClick={this.postIt}>Send</Button>);

        const chips = this.state.tags.map(tag =>
            <Chip key={tag.id}
                  label={tag.tagName}
                  removable
                  onClick={()=>this.removeTag(tag)}
            />);

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
                        ref={this.titleTextField}
                    />
                    <TextField
                        id="author"
                        label={"Author"}
                        required={true}
                        ref={this.authorTextField}
                    />
                    <TextField
                        id="content"
                        label="Content"
                        rows={5}
                        required={true}
                        ref={this.contentTextField}
                    />
                    {chips}
                    <TextField
                        className = "md-cell"
                        id="tagsField"
                        label="Tags"
                        ref={this.tagsTextField}
                    />

                <Button flat primary swapTheming className="md-cell" onClick={this.addTag}>Add Tag</Button>
                </DialogContainer>
            </div>
        );
    }
}