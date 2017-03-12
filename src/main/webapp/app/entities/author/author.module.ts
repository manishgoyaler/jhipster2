import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jhipster2SharedModule } from '../../shared';

import {
    AuthorService,
    AuthorPopupService,
    AuthorComponent,
    AuthorDetailComponent,
    AuthorDialogComponent,
    AuthorPopupComponent,
    AuthorDeletePopupComponent,
    AuthorDeleteDialogComponent,
    authorRoute,
    authorPopupRoute,
    AuthorResolvePagingParams,
} from './';

let ENTITY_STATES = [
    ...authorRoute,
    ...authorPopupRoute,
];

@NgModule({
    imports: [
        Jhipster2SharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AuthorComponent,
        AuthorDetailComponent,
        AuthorDialogComponent,
        AuthorDeleteDialogComponent,
        AuthorPopupComponent,
        AuthorDeletePopupComponent,
    ],
    entryComponents: [
        AuthorComponent,
        AuthorDialogComponent,
        AuthorPopupComponent,
        AuthorDeleteDialogComponent,
        AuthorDeletePopupComponent,
    ],
    providers: [
        AuthorService,
        AuthorPopupService,
        AuthorResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jhipster2AuthorModule {}
