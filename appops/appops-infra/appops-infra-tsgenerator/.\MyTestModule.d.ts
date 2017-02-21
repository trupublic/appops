declare module MyTestModule {

    export interface MyService {
        sayHello(): string;
    }

    export interface TestClass {
        getTestMethod(): void;
    }

    export interface NativeRegExp {
        lastIndex: number;
        test(arg0: string): boolean;
        exec(arg0: string): any;
    }

}
