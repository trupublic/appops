class ServiceMethod {
  service: string;
  method: string;
  arguments: Object[];
  ref: string;
}

export interface Callback<T> {
  onSuccess(data: T): void;
  onError(): void;
}

export interface Service {
  get(): void;
  post(): void;
}

export class HttpService<T> implements Service {
  constructor(private serviceMethod: ServiceMethod, private cb: Callback<T>) {}

  private getQueryString(): string {
    return JSON.stringify(this.serviceMethod);
  }

  private getRequestUri() {
    return document.getElementsByTagName('base')[0].href + "infraEntryPoint" + '?q=' + this.getQueryString();
  }

  public get(): void {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', encodeURI(this.getRequestUri()), true);

    xhr.onload = () => {
      if (xhr.status === 200)
        this.cb.onSuccess(JSON.parse(xhr.responseText));
      else
        this.cb.onError();
    };

    xhr.send();
  }

  public post(): void {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', document.getElementsByTagName('base')[0].href + "/infraEntryPoint", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhr.onload = () => {
      if (xhr.status === 200)
        this.cb.onSuccess(JSON.parse(xhr.responseText));
      else
        this.cb.onError();
    };

    xhr.send(this.getQueryString());
  }
}

export function HttpGet(service: string) {
  return (target: Object, key: string, value: TypedPropertyDescriptor<any>) => {
    return {
      value: function(...args: any[]) {
        const method: ServiceMethod = new ServiceMethod();

        method.service = service;
        method.method = key;
        method.arguments = [];
        method.ref = "hello";//Common.getInitiator();

        let count: number = 0;

        args.forEach(value => {
            if (count != args[length])
              method.arguments.push(value);
            count++;
        });

        const httpService = new HttpService(method, args[args.length - 1]);
        httpService.get();
        const result = value.value.apply(this, args);
      }
    }
  }
}
