/// <reference path='./Http.ts'/>
/// <reference path='./Common.ts'/>

declare module JsHost {

    export interface Country {
        country: string;
        city: string;
        latitude: number;
        longitude: number;
        createdOn: number;
        fetchSomeDate(): number;
    }

    export interface CountryFilter{
      count : number ;
    }

    export interface CountryService<T extends Http.Callback<JsHost.Country[]>>{
        getCountryList(back: T) : void ;
        getCountriesFiltered(filter : CountryFilter, back : T) : void ;
    }
}
