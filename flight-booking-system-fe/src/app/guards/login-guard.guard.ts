import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";


@Injectable({
    providedIn: 'root'
})

export class LoginGuardGuard implements CanActivate {
    constructor(private router: Router) { }

    canActivate(
        route: ActivatedRouteSnapshot,
        satte: RouterStateSnapshot
    ): boolean {
        if (localStorage.getItem("isValid") == 'true') {
            return true;
        } else {
            this.router.navigate(['admin/login']);
            return false;
        }
    }
}