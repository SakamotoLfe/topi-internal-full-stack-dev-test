import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  path = '/topi/api/';

  constructor(private http: HttpClient) {
  }

  GET(path: String): Observable<any> {
    return this.http.get(this.path + path);
  }

  PUT(path: String, object: object): Observable<any> {
    return this.http.put(this.path + path, object);
  }

  POST(path: String, object: object): Observable<any> {
    return this.http.post(this.path + path, object);
  }

  PATCH(path: String, object: object): Observable<any> {
    return this.http.patch(this.path + path, object);
  }

  DELETE(path: String, id: number): Observable<any> {
    return this.http.delete(`${this.path + path}/${id}`);
  }
}
