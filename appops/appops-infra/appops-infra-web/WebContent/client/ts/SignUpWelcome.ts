
class WindowCalculator{

    public getScreenWidth() : number{
         return window.innerWidth ;   
    }

    public getScreenHeight() : number {
        return window.innerHeight ;
    }

    public getGeolocation() : Geolocation {
        return window.clientInformation.geolocation ;
    }
}

class VideoTagGenerator{

    public getTagToEmbed() : string{

        var width : number = 853 ;
        var height : number = 480 ; 
        /**  - this section is omitted for css testing */ 
        return "<iframe width=\"" + width + "\" \" height=\"" + height + "\" src=\"https://www.youtube.com/embed/TK-NDtglCVE?rel=0&amp;showinfo=0\" frameborder=\"0\" \" frameborder=\"0\" allowfullscreen></iframe>" ; 
    }
}

class SignUpWelcome{
       
    public main(): number {

        var element : Element  = document.getElementById("trupublic-video");        
        element.innerHTML = new VideoTagGenerator().getTagToEmbed() ;

        return 0;
    }

    public searchText(key : string , value : string){

    }

    treeRegistry =  {};
    objtest : String ;

     
}

/*var appObj : Object ;
var textObj : string ;
var obj : Object ;
var welcome = new SignUpWelcome();
welcome.main();
*/


