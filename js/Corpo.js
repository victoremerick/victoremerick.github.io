export class Corpo extends React.Component{

    renderNoticia(titulo, resumo, data){
        return (
            <ResumoNoticia titulo={titulo} resumo={resumo} data={data}/>
        );
    }

    renderNoticiaImagem(titulo, resumo, data, imagem = ""){
        return (
            <ResumoNoticiaImagem titulo={titulo} resumo={resumo} data={data} imagem={imagem}/>
        );
    }

    render(){
        return (
            <section>
                {this.renderNoticiaImagem("Titulo1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam tincidunt bibendum accumsan. Nullam non tincidunt tortor, ut vehicula enim. In feugiat scelerisque leo. Vivamus varius quam vitae ipsum fringilla, vel gravida mauris egestas. Aenean molestie lacus aliquam augue feugiat consectetur. Aenean vel vulputate diam. Sed eleifend leo in massa scelerisque interdum. Suspendisse iaculis erat eget velit rutrum efficitur. Duis et dolor tortor. Suspendisse potenti. Etiam pulvinar sapien at nulla rhoncus, non rhoncus dui commodo. Curabitur sodales aliquam arcu, ac suscipit tellus ultrices at.", "01/02/19", "")}
                {this.renderNoticia("Titulo1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam tincidunt bibendum accumsan. Nullam non tincidunt tortor, ut vehicula enim. In feugiat scelerisque leo. Vivamus varius quam vitae ipsum fringilla, vel gravida mauris egestas. Aenean molestie lacus aliquam augue feugiat consectetur. Aenean vel vulputate diam. Sed eleifend leo in massa scelerisque interdum. Suspendisse iaculis erat eget velit rutrum efficitur. Duis et dolor tortor. Suspendisse potenti. Etiam pulvinar sapien at nulla rhoncus, non rhoncus dui commodo. Curabitur sodales aliquam arcu, ac suscipit tellus ultrices at.", "01/02/19")}
                {this.renderNoticiaImagem("Titulo1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam tincidunt bibendum accumsan. Nullam non tincidunt tortor, ut vehicula enim. In feugiat scelerisque leo. Vivamus varius quam vitae ipsum fringilla, vel gravida mauris egestas. Aenean molestie lacus aliquam augue feugiat consectetur. Aenean vel vulputate diam. Sed eleifend leo in massa scelerisque interdum. Suspendisse iaculis erat eget velit rutrum efficitur. Duis et dolor tortor. Suspendisse potenti. Etiam pulvinar sapien at nulla rhoncus, non rhoncus dui commodo. Curabitur sodales aliquam arcu, ac suscipit tellus ultrices at.", "01/02/19")}
                {this.renderNoticia("Titulo1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam tincidunt bibendum accumsan. Nullam non tincidunt tortor, ut vehicula enim. In feugiat scelerisque leo. Vivamus varius quam vitae ipsum fringilla, vel gravida mauris egestas. Aenean molestie lacus aliquam augue feugiat consectetur. Aenean vel vulputate diam. Sed eleifend leo in massa scelerisque interdum. Suspendisse iaculis erat eget velit rutrum efficitur. Duis et dolor tortor. Suspendisse potenti. Etiam pulvinar sapien at nulla rhoncus, non rhoncus dui commodo. Curabitur sodales aliquam arcu, ac suscipit tellus ultrices at.", "01/02/19")}
                {this.renderNoticia("Titulo1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam tincidunt bibendum accumsan. Nullam non tincidunt tortor, ut vehicula enim. In feugiat scelerisque leo. Vivamus varius quam vitae ipsum fringilla, vel gravida mauris egestas. Aenean molestie lacus aliquam augue feugiat consectetur. Aenean vel vulputate diam. Sed eleifend leo in massa scelerisque interdum. Suspendisse iaculis erat eget velit rutrum efficitur. Duis et dolor tortor. Suspendisse potenti. Etiam pulvinar sapien at nulla rhoncus, non rhoncus dui commodo. Curabitur sodales aliquam arcu, ac suscipit tellus ultrices at.", "01/02/19")}
            </section>
        );
    }
}