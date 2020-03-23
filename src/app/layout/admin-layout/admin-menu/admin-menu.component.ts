import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-menu',
  templateUrl: './admin-menu.component.html',
  styleUrls: ['./admin-menu.component.css']
})
export class AdminMenuComponent implements OnInit {

  isUserCollapsed = false;
  isClientCollapsed = false;
  isAccountCollapsed = false;
  isCodesCollapsed = false;
  isProgettiCollapsed=false;
  isSupportCollapsed=false;

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  logout() {
    localStorage.clear();
    this.router.navigateByUrl('');
  }

  userscollapse() {
    if (this.isUserCollapsed === false) {
      this.isUserCollapsed = true;
    } else { this.isUserCollapsed = false; }
  }

  accountcollapse() {
    if (this.isAccountCollapsed === false) {
      this.isAccountCollapsed = true;
    } else { this.isAccountCollapsed = false; }
  }

    codestocollapse() {
      if (this.isCodesCollapsed === false) {
        this.isCodesCollapsed = true;
      } else { this.isCodesCollapsed = false; }
    }
    progettitocollapse() {
      if (this.isProgettiCollapsed === false) {
        this.isProgettiCollapsed = true;
      } else { this.isProgettiCollapsed = false; }
    }

    supportcollapse() {
      if (this.isSupportCollapsed === false) {
        this.isSupportCollapsed = true;
      } else { this.isSupportCollapsed = false; }
    }
}
