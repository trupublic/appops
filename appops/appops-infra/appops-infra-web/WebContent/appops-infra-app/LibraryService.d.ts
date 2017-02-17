/// <reference path='./Http.ts'/>

declare module LibraryService {

    export interface LibraryService {
        getMyBooks(arg0: string, back: Http.Callback<String>) : void;
    }

}
