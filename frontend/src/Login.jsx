import {TextField, FontIcon} from "react-md";
import React, {PureComponent} from "react";

export default class Login extends PureComponent {
    render() {
        return (
            <div className="md-grid">
                <TextField
                    id="userNameField"
                    lineDirection="center"
                    placeholder={"Username"}
                    resize={{min: 320, max: 500}}
                    leftIcon={<FontIcon>search</FontIcon>}
                    className="md-cell md-cell--bottom "
                />
                <TextField
                    id="passwordField"
                    lineDirection="center"
                    placeholder={"Password"}
                    resize={{min: 320, max: 500}}
                    leftIcon={<FontIcon>search</FontIcon>}
                    className="md-cell md-cell--bottom "
                />
            </div>
        );
    }
}