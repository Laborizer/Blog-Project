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
                    resize={{min: 120, max: 180}}
                    className="md-cell md-cell--right "
                />
                <TextField
                    id="passwordField"
                    lineDirection="center"
                    placeholder={"Password"}
                    resize={{min: 120, max: 180}}
                    className="md-cell md-cell--right "
                />
            </div>
        );
    }
}