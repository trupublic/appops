"use strict";

module Http {

    class ServiceMethod {
        service: String;
        method: String;
        arguments: Object[];
        ref : String;
    }

    export interface Callback<T> {

        onSuccess(data: T): void;
        onError(): void;
    }

    export interface Service {

        get(): void;
        post(): void;

    }

    export class HttpDefaultCallback implements Callback<void>{

        onSuccess(data: void): void {
           console.log("service call finished");
        }

        onError(): void {

        }
    }

    export class HttpService<X> implements Service {

        serviceMethod: ServiceMethod;
        back: Callback<X>;

        constructor(m: ServiceMethod, back: Callback<X>) {
            this.serviceMethod = m;
            this.back = back;
        }

        private getQueryString(): string {
            return JSON.stringify(this.serviceMethod);
        }

        /*
          access the base href value set in html page
        */
        private getRequestUri() {
            return document.getElementsByTagName('base')[0].href + "/infraEntryPoint" + '?q=' + this.getQueryString();
        }

        get(): void {
            var xhr = new XMLHttpRequest();
            var that = this.back;
            xhr.open('GET', encodeURI(this.getRequestUri()), true);
            xhr.onload = function() {
                if (xhr.status === 200) {
                    that.onSuccess(JSON.parse(xhr.responseText));
                }
                else {
                    that.onError();
                }
            };
            xhr.send();
        }

        post(): void {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', document.getElementsByTagName('base')[0].href + "/infraEntryPoint", true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

            xhr.onload = function() {
                if (xhr.status === 200) {
                    this.back.onSuccess(JSON.parse(xhr.responseText));
                }
                else {
                    this.back.onError();
                }
            };
            xhr.send(this.getQueryString());
        }
    }

    export function HttpGet(service: String) {

        var that = service;

        return (target: Object, key: string, value: TypedPropertyDescriptor<any>) => {

            // console.log(that, target, key, value);

            return {
                value: function(...args: any[]) {

                  //  console.log( "Http Get of > " + that, target, key, value , "parameters are => " + args);

                    var method: ServiceMethod = new ServiceMethod();

                    method.service = that;

                    method.method = key;

                    method.arguments = [];
                    method.ref = Common.getInitiator();

                    var count = 0;
                    args.forEach(value => {
                        if (count != args[length])
                            method.arguments.push(value);
                        count++;
                    });

                    // the second parameter is the last parameter to the source method and is the call back.
                    // first parameter has all thats needed to identify the method
                    var service = new Http.HttpService(method, args[args.length - 1]);
                    service.get();
                    var result = value.value.apply(this, args);
                }
            };
        }
    }
}
