/// <reference path='./Benchmark.d.ts'/>
/// <reference path='./JsHost.d.ts'/>
/// <reference path='./Http.ts'/>
/// <reference path='./Common.ts'/>
/// <reference path='./Dashboard.ts'/>
/// <reference path='./Provision.d.ts' />
/// <reference path='./Provision.ts' />
/// <reference path="./LibraryService.ts"/>



class CountryServiceCallback implements Http.Callback<JsHost.Country[]>{

    @Common.BenchMark(false)
    onSuccess(data: JsHost.Country[]): void {
        countryApp.setData(data);
        countryApp.drawVisuals();
    }

    onError(): void {
        var console: Console;
        console.debug("Error");
    }
}

// class CountryFilter implements JsHost.CountryFilter{
//   count : number ;
//
// }

class CountryServiceClient implements JsHost.CountryService<CountryServiceCallback> {

    // @Common.BenchMark(false)
    @Http.HttpGet("CountryService")
    public getCountryList(back: CountryServiceCallback) {
        // Nothing here.. magic is in the annotaion that automates the http call
        //console.log("CountryService getCountryList called");
    }

    @Http.HttpGet("CountryService")
    // @Common.BenchMark(false)
    public getCountriesFiltered(filter: JsHost.CountryFilter, back: CountryServiceCallback) {
        // Nothing here.. magic is in the annotaion that automates the http call
        //console.log("CountryService getCountriesFiltered called");
    }

}

class App {

    private countryList: JsHost.Country[] = null;
    private visuals: Dashboard.Visual<Object>[] = new Array(); // (Debasish) Need fixing later

    public setData(d: JsHost.Country[]) {
        this.countryList = d;
    }

    public switchOp() {

        var client = new ProvisionImpl.ProvisionServiceClient();

        var noop = Provision.ProvisionMode.NOOP;
        var prod = Provision.ProvisionMode.PRODUCTION;

        if (this.op) {
            client.setGlobalProvisionMode(noop, "dummy", new Http.HttpDefaultCallback());
        } else {
            client.setGlobalProvisionMode(prod, "dummy", new Http.HttpDefaultCallback());
        }
    }

    public refreshDashboard() {
        document.getElementById("countries").innerHTML = "";
        var count = parseInt((<HTMLInputElement>document.getElementById("count")).value);
        this.fetchAndDraw(count);
    }

    public clearElements() {
        this.visuals.forEach((value) => {
            value.clear();
        });
    }

    public drawVisuals() {
        var that = this;
        this.visuals.forEach((visual) => {
            visual.setData(that.countryList);
            visual.draw();
        });
    }

    @Common.BenchMark(false)
    public fetchAndDraw(count: number) {

        var back = new CountryServiceCallback();

        let filter: JsHost.CountryFilter = <JsHost.CountryFilter>{};

        filter.count = count;

        var countries = new CountryServiceClient();

        countries.getCountriesFiltered(filter, back);
    }

    public clearAll() {
        document.getElementById("countries").innerHTML = "";
        var canvas = (<HTMLCanvasElement>document.getElementById("topCanvas"));
        canvas.getContext("2d").clearRect(0, 0, canvas.width, canvas.height);
        document.getElementById("benchmarks").innerHTML = "";
    }

    @Common.BenchMark(true)
    public start() {
        var that = this;
        this.visuals.push(new Dashboard.DataList("countries"));
        this.visuals.push(new Dashboard.ScatterChart("topCanvas"));
        that.fetchAndDraw(10);

      var impl  = new Library.LibraryServiceImpl() ;
      var back = new Library.LibrayServiceCallback();

      impl.getMyBooks("Debasish" , back);
    }

    public showBenchmarks(): void {
        Common.displayBenchmarks("benchmarks");
    }

    op: boolean = true;

}

var countryApp = new App();
countryApp.start();
