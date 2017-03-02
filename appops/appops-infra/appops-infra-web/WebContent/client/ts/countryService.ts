import {Callback, HttpGet} from './http';


export interface Callback<T> {
    onSuccess(data: T): void;
    onError(): void;
  }

  export interface Country {
    country: string;
    city: string;
    latitude: number;
    longitude: number;
    createdOn: number;
    fetchSomeDate(): number;
  }

  export interface CountryFilter {
    count: number;
  }

  export interface CountryService<T extends Callback<Country[]>> {
    getCountryList(back: T): void;
    getCountriesFiltered(filter: CountryFilter, back: T): void;
  }



export class CountryServiceCallback implements Callback<Country[]> {

    constructor(public onSuccess: (data: Country[]) => void, public onError: () => void) {}

    // @Common.BenchMark(false)
    // onSuccess(data: JsHost.Country[]): void {
    //     // countryApp.setData(data);
    //     // countryApp.drawVisuals();
    //     console.log(data);
    // }
    //
    // onError(): void {
    //     console.debug("Error");
    // }
}

export class CountryServiceClient implements CountryService<CountryServiceCallback> {

    // @Common.BenchMark(false)
    @HttpGet("CountryService")
    public getCountryList(back: CountryServiceCallback) {
        // Nothing here.. magic is in the annotaion that automates the http call
        //console.log("CountryService getCountryList called");
    }

    @HttpGet("CountryService")
    // @Common.BenchMark(false)
    public getCountriesFiltered(filter: CountryFilter, back: CountryServiceCallback) {
        // Nothing here.. magic is in the annotaion that automates the http call
        //console.log("CountryService getCountriesFiltered called");
    }

}
