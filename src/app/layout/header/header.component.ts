import {Component, OnInit, ViewChild} from '@angular/core';
import { UserDTO } from 'src/dto/userdto';
import {MatMenuTrigger} from "@angular/material/menu";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;

  user: UserDTO = new UserDTO;
  constructor() { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }
  someMethod() {
    this.trigger.openMenu();
  }

}
