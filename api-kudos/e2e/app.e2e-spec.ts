import { KudosPage } from './app.po';

describe('kudos App', () => {
  let page: KudosPage;

  beforeEach(() => {
    page = new KudosPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
