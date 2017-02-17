"use strict"

module Dashboard {

  /*
      T - the tag that this visual will attach to
      M - the data this visual will use
  */
    export interface Visual<M extends Object> {

        elementName: String; // the topmost element to use for this visual component
        data: M[];

        setData(data : M[]) :void ;
        draw(): void;
        clear(): void;
    }

    export class DataList implements Visual<JsHost.Country> {

        elementName: String;
        data: JsHost.Country[];
        element : HTMLDivElement ;

        constructor(ele: String) {
            this.elementName = ele;
            this.element = <HTMLDivElement>document.getElementById(this.elementName.toString());
        }

        clear(): void {
            document.getElementById(this.elementName.toString()).innerHTML = "No records yet";
        }

        draw(): void {
            document.getElementById(this.elementName.toString()).innerHTML = "<b> city </b>" + ` ` + "<b> country </b>" + ` ` + "<b> latitude </b>" + ` ` + "<b> longitude </b>" + ` ` + "<b> createdon </b>";
            this.data.forEach((value) => {
                this.drawRecord(value);
            });
        }

        drawRecord(obj: JsHost.Country) {
            var ele: HTMLDivElement = <HTMLDivElement>document.createElement('div');
            ele.innerText = obj.city + ` ` + obj.country + ` ` + obj.latitude + ` ` + obj.longitude + ` ` + obj.createdOn;
            document.getElementById(this.elementName.toString()).appendChild(ele);
        }

        public setData(data : JsHost.Country[]){
            this.data = data ;
        }
    }

    export class ScatterChart implements Visual<JsHost.Country> {

        elementName: String;
        element : HTMLCanvasElement ;

        radiusSize: number = 3;
        data: JsHost.Country[];
        numberOfElements: number = 100;

        public constructor(ele: String) {
            this.elementName = ele.toString();
            this.element = <HTMLCanvasElement>document.getElementById(this.elementName.toString());
        }

        init() {
            this.element = <HTMLCanvasElement>document.getElementById(this.elementName.toString());
        }

        public setSize() {
            this.element.width = this.element.parentElement.clientWidth - 20;
            this.element.height = this.element.parentElement.clientHeight - 20;
        }

        public clear() {
            var context = this.element.getContext('2d');
            context.clearRect(0, 0, this.element.width, this.element.height);
        }

        public draw() {
            //this.drawRects(this.canvas);
            if (this.element == null) this.init();

            this.drawCircles();
        }

        public drawCircles() {
            var context = this.element.getContext('2d');
            var centerX = this.element.width / 2;
            var centerY = this.element.height / 2;

            for (var i = 0; i < this.data.length; i++) {
                var x = Math.abs(this.data[i].longitude) * 2;// * this.element.width;
                var y = Math.abs(this.data[i].latitude) * 2;// * this.element.height;
                this.drawCircle(context, x, y, this.radiusSize, 'green');
            }
        }

        public drawCircle(context: any, x: number, y: number, radius: number, color: string) {
            context.beginPath();
            context.arc(x, y, radius, 0, 2 * Math.PI, false);
            context.fillStyle = color;
            context.fill();
            context.lineWidth = 0.5;
            context.strokeStyle = '#003300';
            context.stroke();
            context.closePath();
        }

        public drawRects() {
            var context = this.element.getContext('2d');
            var centerX = this.element.width / 2;
            var centerY = this.element.height / 2;
            var fillStyles: any[];
            var squareSize = this.radiusSize * 2;
            for (var i = 0; i < this.numberOfElements; i++) {
                var x = Math.abs(this.data[i].latitude * this.element.width);
                var y = Math.abs(this.data[i].longitude * this.element.height);
                this.drawRect(context, x, y, this.radiusSize * 2, 'green');
            }
        }

        public drawRect(context: any, x: number, y: number, squareSize: number, color: string) {
            context.fillStyle = color;
            context.fillRect(x, y, squareSize, squareSize);
            context.lineWidth = 1;
            context.strokeStyle = '#000000';
            context.strokeRect(x, y, squareSize, squareSize);
        }

        public setData(data : JsHost.Country[]){
            this.data = data ;
        }
    }
}
