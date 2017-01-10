<html lang="en">
<head>
    <meta charset="utf-8">
    <title>${title}</title>

    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/error.css" rel="stylesheet">

</head>

<body class="overflow-hidden">
<div class="wrapper no-navigation preload">
    <div class="error-wrapper">
        <div class="error-inner">
            <div class="error-type animated">${status?if_exists}</div>
            <h1>没有权限</h1>
            <div class="m-top-md">
                <a href="/" class="btn btn-default btn-lg text-upper">Back to Home</a>
            </div>
        </div><!-- ./error-inner -->
    </div><!-- ./error-wrapper -->
</div><!-- /wrapper -->

</body>

</html>
