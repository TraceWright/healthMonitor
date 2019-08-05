import React, { useState } from 'react';
import { Redirect } from "react-router-dom";
import './menu.scss';

interface IProps {}
interface IState {
    activeItem: string;
    menuItems: Array<{ text: string}>,
    redirect: boolean,
}

class MenuContainer extends React.PureComponent<IProps, IState> {
    constructor(props: IProps) {
      super(props)
      
      this.state = {
        activeItem: '',
        menuItems: [
            { text: 'Home' },
            { text: 'Blood Pressure' },
            { text: 'Egfr' },
            // { text: 'History' },
            { text: 'Contact' },
        ],
        redirect: false,
      }
      
      this.handleClick = this.handleClick.bind(this)
    }

    componentDidUpdate() {
      this.setState({ redirect: false });
    }
    
    handleClick(activeItem: any) {
      return (e: any) => {
        e.preventDefault()      
        this.setState({
          activeItem,
          redirect: true,
        })
      }
    }
    
    render() {
        if (this.state.redirect) {
            return <Redirect push to={this.state.activeItem.replace(' ', '')} />;
        }
        const menuItems = this.state.menuItems.map(item => <MenuItem item={item} handleClick={this.handleClick} key={item.text}/>)
        return (
            <React.Fragment>
              <div className='menu-container' >
                { menuItems }
              </div>
            </React.Fragment>
        )
    }
  }
 
function MenuItem(props: any) {
    const [hovered, setHovered] = useState(false);
    const toggleHover = () => setHovered(!hovered);
    return (
        <div 
            id={props.item.text}
            onClick={props.handleClick(props.item.text)}
            className={hovered ? 'menu-item menu-item--hover' : 'menu-item'}
            onMouseEnter={toggleHover}
            onMouseLeave={toggleHover}
        >
            { props.item.text.toUpperCase() }
        </div>
    )
}

export default MenuContainer;
