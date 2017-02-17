declare module JsTestHost {

    export interface CountryFilter {
        count: number;
    }

    export interface Country {
        country: string;
        city: string;
        latitude: number;
        longitude: number;
        createdOn: number;
        fetchSomeDate(): number;
    }

    export interface NativeRegExp {
        lastIndex: number;
        test(arg0: string): boolean;
        exec(arg0: string): any;
    }

}
