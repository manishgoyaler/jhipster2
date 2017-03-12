import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { AuthorComponent } from './author.component';
import { AuthorDetailComponent } from './author-detail.component';
import { AuthorPopupComponent } from './author-dialog.component';
import { AuthorDeletePopupComponent } from './author-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class AuthorResolvePagingParams implements Resolve<any> {

  constructor(private paginationUtil: PaginationUtil) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      let page = route.queryParams['page'] ? route.queryParams['page'] : '1';
      let sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
      return {
          page: this.paginationUtil.parsePage(page),
          predicate: this.paginationUtil.parsePredicate(sort),
          ascending: this.paginationUtil.parseAscending(sort)
    };
  }
}

export const authorRoute: Routes = [
  {
    path: 'author',
    component: AuthorComponent,
    resolve: {
      'pagingParams': AuthorResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jhipster2App.author.home.title'
    }
  }, {
    path: 'author/:id',
    component: AuthorDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jhipster2App.author.home.title'
    }
  }
];

export const authorPopupRoute: Routes = [
  {
    path: 'author-new',
    component: AuthorPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jhipster2App.author.home.title'
    },
    outlet: 'popup'
  },
  {
    path: 'author/:id/edit',
    component: AuthorPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jhipster2App.author.home.title'
    },
    outlet: 'popup'
  },
  {
    path: 'author/:id/delete',
    component: AuthorDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'jhipster2App.author.home.title'
    },
    outlet: 'popup'
  }
];
