import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  static messages: string[] = [];

  add(message: string) {
    MessageService.messages.push(message);
    console.log(message);
  }

  clear() {
    MessageService.messages = [];
  }
}
