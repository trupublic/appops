


declare module Provision {

   export enum ProvisionMode {
      NOOP,
      PRODUCTION,
      DEVELOPMENT,
      TEST,
      DEFAULT
   }

    export interface ProvisionSettingsService {
        setGlobalProvisionMode(arg0: ProvisionMode, arg1: string, back: Http.HttpDefaultCallback): void;
        getGlobalProvisionMode(arg0: string, back: Http.HttpDefaultCallback): void;
        setPathProvision(arg0: string, arg1: ProvisionMode, back: Http.HttpDefaultCallback): void;
        getPathProvision(arg0: string, back: Http.HttpDefaultCallback): void;
    }

}
