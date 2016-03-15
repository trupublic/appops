/// <reference path='./Benchmark.d.ts'/>

"use strict"

module Common {

    export var globalBenchmarks = new Object();

    // initiation time is used as a id to identify the benchmark sequence
    function initiateBenchmark(name: string, obj: Object, time: number, target: Object) {

        var ssbm = new SingleSequenceBenchmark();

        ssbm.recordStart(name, name, obj, time, target);

        globalBenchmarks[name] = ssbm;
    }

    export function getInitiator() : string {
       return  Object.keys(Common.globalBenchmarks).slice(0)[0] ;
    }

    export function displayBenchmarks(eleName: string) {

        var key: string;
        var ele: HTMLDivElement = <HTMLDivElement>document.getElementById(eleName);

        var  impl :  BenchmarkingServiceHttpImpl = new BenchmarkingServiceHttpImpl();

        impl.getBenchmarkList(Common.getInitiator() , new Common.BenchmarkingCallback(eleName) ) ;

        for (key in Common.globalBenchmarks) {

            var ssbm: SingleSequenceBenchmark = Common.globalBenchmarks[key];

            var entry: BenchmarkEntry;

            for (var indx = 0; indx < ssbm.benchmarkStack.length; indx++) {
                var child = document.createElement("div");
                child.innerHTML = ssbm.benchmarkStack[indx].toString() ;
                ele.appendChild(child);
            }
        }
    }

    function getBenchmarkSequence(ini : boolean , key: string): SingleSequenceBenchmark {

        var ssbm: SingleSequenceBenchmark;

        if (key == null)
            ssbm = globalBenchmarks[Object.keys(globalBenchmarks).pop()];
        else
            ssbm = globalBenchmarks[key];

        if (ssbm == null && ini == true) {
            ssbm = new SingleSequenceBenchmark();
            globalBenchmarks[key] = ssbm ;
        }else{
           ssbm = globalBenchmarks[Object.keys(globalBenchmarks).pop()];
        }

        return ssbm;

    }

    class BenchmarkEntry {

        initiator : string ;
        callOnObject: Object;
        callTargetMethod: String;
        time: number;
        start: boolean;

        public toString() : string {
           return this.callTargetMethod + "-" + this.time + " - " ;
        }

    }

    class SingleSequenceBenchmark {

        benchmarkStack: BenchmarkEntry[] = [];


        getBenchMarksToLog()  : Benchmark.Benchmark[] {

          var bmToLog :  Benchmark.Benchmark[] = [] ;

            for ( var b of this.benchmarkStack){
                 var bm =  <Benchmark.Benchmark> {} ;
                 bm.topic = b.initiator ;
                 bm.millis = b.time ;
                 bm.item = b.callTargetMethod.toString() ;
                 bmToLog.push(bm);
            }

            return bmToLog ;
        }

        getEntries(): BenchmarkEntry[] {
            return this.benchmarkStack;
        }

        recordStart(ini: string , name: String, obj: Object, start: number, target: Object) {

            var entry: BenchmarkEntry = new BenchmarkEntry();
            entry.initiator = ini ;
            entry.callOnObject = obj;
            entry.callTargetMethod = name;
            entry.time = start;
            entry.start = true;
            this.benchmarkStack.push(entry);

        }

        recordFinish( ini: string, name: String, obj: Object, finish: number, target: Object) {

            var entry: BenchmarkEntry = new BenchmarkEntry();
            entry.initiator = ini ;
            entry.callOnObject = obj;
            entry.callTargetMethod = name;
            entry.time = finish;
            entry.start = false;

            this.benchmarkStack.push(entry);
        }

    }


    export function BenchMark(initiator: boolean) {

        var initiate = initiator; // the string specified when you added the Benchmark annotation
        var initMethod = "" ;

        return (target: Object, key: string, value: TypedPropertyDescriptor<any>) => {

            // target = object on which source method is invoked
            // key = source method name
            // value = function object depicting the source method prototypes

            console.log("BenchMark factory > target is > ", target, " > key is > ", key, " > value is >", value);

            // initiation time is used as a id to identify the benchmark sequence
            return {
                value: function(...args: any[]) {

                    var methodName = ("" + target.constructor).split("function ")[1].split("(")[0] + "." + key;

                    var start = window.performance.now();

                    if (initiate === true) {
                        initiateBenchmark(methodName, target, start, value);
                        initMethod = methodName ;
                    } else {
                        var ssbm = getBenchmarkSequence(initiate, methodName);
                        ssbm.recordStart(initMethod, methodName, target, start, value);
                    }

                    console.log("BM of > " + methodName + "() started at " + start + " with ", " > target is > ", target, " > key is > ", key, " > value is >", value);

                    var result = value.value.apply(this, args);

                    var ssbm = getBenchmarkSequence(initiate , methodName);

                    ssbm.recordFinish(initMethod, methodName, target, window.performance.now(), value);

                    if(initiate == true){
                      // var  impl :  BenchmarkingServiceHttpImpl = new BenchmarkingServiceHttpImpl();
                      //impl.logTopicDetailed(methodName, window.performance.now() - start, key, new Http.HttpDefaultCallback());
                      // impl.logTopicBatches(ssbm.getBenchMarksToLog() , new Http.HttpDefaultCallback() ) ;
                    }

                    console.log("BM of >" + methodName + `: took - ` + (window.performance.now() - start).toString() + " with ", " > target is > ", target, " > key is > ", key, " > value is >", value);

                }
            }
        };
    }

    export class BenchmarkingCallback implements Http.Callback<Benchmark.Benchmark[]> {

        element : string ;

        constructor(ele : string){
           this.element = ele ;
        }

        onSuccess(data: Benchmark.Benchmark[]): void {

            var benchmarksElement: HTMLDivElement = <HTMLDivElement>document.getElementById(this.element);

            for (var bench of data) {
                var div: HTMLDivElement = document.createElement('div');
                div.innerText = bench.topic + " " + bench.millis + + bench.item;
                benchmarksElement.appendChild(div);
            }
        }

        onError(): void {

        }

    }

    export class BenchmarkingServiceHttpImpl implements Benchmark.BenchmarkingService {

        @Http.HttpGet("BenchmarkingService")
        logTopicDetailed(arg0: string, arg1: number, arg2: string, back: Http.Callback<void>): void {

        }

        @Http.HttpGet("BenchmarkingService")
        getBenchmarkList(arg0: string, back: Http.Callback<Benchmark.Benchmark[]>): void {

        }

        @Http.HttpGet("BenchmarkingService")
        logTopic(arg0: Benchmark.Benchmark, back: Http.Callback<void>): void {

        }

        @Http.HttpGet("BenchmarkingService")
        logTopicBatches(arg0: Benchmark.Benchmark[], back: Http.Callback<void>): void {

        }
    }


}
