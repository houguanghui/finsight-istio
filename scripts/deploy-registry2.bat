docker run -d -v F:\registry2:/var/lib/registry -p 5000:5000 --name registry-2 registry:2

 docker tag 26b2eb03618e 127.0.0.1:5000/registry:2