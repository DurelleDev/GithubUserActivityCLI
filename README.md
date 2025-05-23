https://roadmap.sh/projects/github-user-activity

Requirements
The application should run from the command line, accept the GitHub username as an argument, fetch the user’s recent activity using the GitHub API, and display it in the terminal. The user should be able to:

Provide the GitHub username as an argument when running the CLI.
github-activity <username>

Fetch the recent activity of the specified GitHub user using the GitHub API. You can use the following endpoint to fetch the user’s activity:
# https://api.github.com/users/<username>events
# Example: https://api.github.com/users/DurelleDev/events

Display the fetched activity in the terminal.
Output:
- Pushed 3 commits to DurelleDev/developer-roadmap
- Opened a new issue in DurelleDev/developer-roadmap
- Starred DurelleDev/developer-roadmap
- ...
  
You can learn more about the GitHub API here.
Handle errors gracefully, such as invalid usernames or API failures.
Use a programming language of your choice to build this project.
Do not use any external libraries or frameworks to fetch the GitHub activity.
