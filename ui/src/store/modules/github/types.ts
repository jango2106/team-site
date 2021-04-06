export interface User {
  id: number;
  name: string;
  avatarUrl: string;
}

export interface Pull {
  id: string;
  author: User;
  url: string;
  reviewers: Array<User>;
  assignees: Array<User>;
  createdDate: string;
  lastUpdatedDate: string;
  repoName: string;
  pullNumber: number;
  isDraft: boolean;
}

export interface GithubState {
  pulls: Array<Pull>;
}
