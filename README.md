<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->


<h3 align="center">Cloud based ActionItems App</h3>
  <p align="center">
    A simple web server. Deployed on AWS. The Hard Way


<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>


<!-- ABOUT THE PROJECT -->
## About The Project

Describe the project and future improvements and tech used

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* [![Spring][Spring]][Spring-url]
* [![Terrafrom][Terraform]][Terraform-url]
* [![Kubernetes][Kubernetes]][Kubernetes-url]
* [![Aws][Aws]][Aws-url]
* [![postgresql][postgresql]][postgresql-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

Some blurb about running the script to deploy an instance of this app on your own aws

### Prerequisites
* AWS Account - run 'aws configure' after installing the cli
* TODO-add more details about setting up aws account/permissions....


You will need the below tools installed and configured

* docker  
```sh
  brew install docker
  ```

* kubectl
  ```sh
  brew install kubectl
  ```
* eksctl
  ```sh
  brew install eksctl
  ```
* AWS cli - https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html
  ```sh
  curl "https://awscli.amazonaws.com/AWSCLIV2.pkg" -o "AWSCLIV2.pkg"
  sudo installer -pkg AWSCLIV2.pkg -target /
  ```

### Installation

* The project is built to simply clone the repo and run the deployment script

1. Clone this repo
2. Have the above command line tools installed and configured
3. 
   ```sh
   sh deploy.sh
   ```
3. Get the load balancer url
   ```sh
   kubectl get svc -o wide
   ```
4. Visit the url
   ```http
   http://loadbalancer:8080/items
   ```

5. Visit the swagger docs
   ```http
   http://loadbalancer:8080/swagger-ui/index.html#/
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- USAGE EXAMPLES -->
## Usage

You now have a todo list server running in aws - get, post, delete. do with it what you will

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- ROADMAP -->
## Roadmap

- [ ] Add support for multiple users
- [ ] Ensure and document security 
- [ ] Feature 3
    - [ ] Nested Feature

See the [open issues](https://github.com/gxwilkerson415/ActionItemsApp/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License

Distributed under the Apache License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[Spring-url]: https://spring.io/
[Spring]: https://img.shields.io/badge/Spring-8015847?style=for-the-badge&logo=Spring&logoColor=white

[postgresql-url]: https://www.postgresql.org/
[postgresql]: https://img.shields.io/badge/postgresql-326CE5?style=for-the-badge&logo=postgresql&logoColor=white


[terraform-url]: https://terraform.io/
[terraform]: https://img.shields.io/badge/terraform-844FBA?style=for-the-badge&logo=terraform&logoColor=white

[Aws-url]: https://aws.amazon.com/eks/
[Aws]: https://img.shields.io/badge/EKS-FF9900?style=for-the-badge&logo=amazoneks&logoColor=white

[kubernetes-url]: https://kubernetes.io/
[kubernetes]: https://img.shields.io/badge/Kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white

