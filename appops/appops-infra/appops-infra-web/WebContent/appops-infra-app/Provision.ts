/// <reference path='./Provision.d.ts'/>
/// <reference path='./Http.ts'/>


"use strict"

module ProvisionImpl{

  enum ProvisionMode {
     NOOP,
     PRODUCTION,
     DEVELOPMENT,
     TEST,
     DEFAULT
  }

export class ProvisionServiceCallback implements Http.Callback<Provision.ProvisionMode>{

    onSuccess(data: Provision.ProvisionMode): void {

    }

    onError(): void {
        var console: Console;
        console.log("provision callback Error");
    }
 }


 export class ProvisionServiceClient  implements Provision.ProvisionSettingsService{

     @Http.HttpGet("ProvisionSettingsService")
     setGlobalProvisionMode(arg0: Provision.ProvisionMode, arg1: string, back : Http.HttpDefaultCallback): void{

     }

     getGlobalProvisionMode(arg0: string , back : Http.HttpDefaultCallback): void{

     }

     setPathProvision(arg0: string, arg1: Provision.ProvisionMode,  back : Http.HttpDefaultCallback): void{

     }

     getPathProvision(arg0: string,  back : Http.HttpDefaultCallback): void{

     }
 }
}
