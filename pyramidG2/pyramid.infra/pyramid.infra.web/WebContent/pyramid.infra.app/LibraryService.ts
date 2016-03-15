/// <reference path='./Http.ts'/>
/// <reference path='./Common.ts'/>

module Library{


    export class Library{

        public doLibrary() : void {


        }

    }

    export class LibraryServiceImpl implements LibraryService.LibraryService{

        @Http.HttpGet("LibraryService")
        getMyBooks(arg0: string, back: Http.Callback<String>) : void{

        }
    }

    export class LibrayServiceCallback implements Http.Callback<String>{

      @Common.BenchMark(false)
      onSuccess(data: String): void {
          console.log(data);
      }

      onError(): void {
          var console: Console;
          console.log("provision callback Error");
      }
    }


}
