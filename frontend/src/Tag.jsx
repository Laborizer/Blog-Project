
import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';
import { Chip } from 'react-md';

export default class Tag extends PureComponent {
  static propTypes = {
    state: PropTypes.shape({
      name: PropTypes.string.isRequired,
      abbreviation: PropTypes.string.isRequired,
    }).isRequired,
    onClick: PropTypes.func.isRequired,
  };

  handleRemove = () => {
    this.props.onClick(this.props.state);
  };

  render() {
    const { state: { name, abbreviation }, ...props } = this.props;
    return (
      <Chip
        {...props}
        className="state-chip"
        onClick={this.handleRemove}
        removable
        label={name}
      />
    );
  }
}