import HeaderApp from "./HeaderApp";
import Menu from "./Menu";
import Corpo from "./HeaderApp";
import SideBar from "./SideBar";
import Rodape from "./Rodape";

export class App extends React.Component {
    render() {
        return (
        <div className="teste">
            <HeaderApp/>
            <Menu />
            <Corpo />
            <SideBar />
            <Rodape />
        </div>
        )
    }
}