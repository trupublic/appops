/// <reference path='./Http.ts'/>

declare module Benchmark {

    export interface BenchmarkingService {
        getBenchmarkList(arg0: string, back: Http.Callback<Benchmark.Benchmark[]>): void;
        logTopicDetailed(arg0: string, arg1: number, arg2: string, back: Http.Callback<void>): void;
        logTopicBatches(arg0: Benchmark[], back: Http.Callback<void>): void;
        logTopic(arg0: Benchmark, back: Http.Callback<void>): void;
    }
    
    export interface Benchmark {
        topic: string;
        millis: number;
        item: string;
        toString(): string;
    }

}
