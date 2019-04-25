class ResumoNoticia extends React.Component{
    constructor(props){
        super(props);
        this.titulo = null;
        this.resumo = null;
        this.data = null;
    }

    render(){
        return (
            <div className="resumo-noticia">
                <h3 className="titulo">
                    {this.props.titulo}
                </h3>
                <p className="resumo">
                    {this.props.resumo}
                </p>
                <p className="data">
                    {this.props.data}
                </p>
            </div>
        );
    }
}