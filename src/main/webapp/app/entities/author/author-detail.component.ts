import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { Author } from './author.model';
import { AuthorService } from './author.service';

@Component({
    selector: 'jhi-author-detail',
    templateUrl: './author-detail.component.html'
})
export class AuthorDetailComponent implements OnInit, OnDestroy {

    author: Author;
    private subscription: any;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private authorService: AuthorService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['author']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.authorService.find(id).subscribe(author => {
            this.author = author;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
