/*
 * Copyright 2017 Shredder121.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.shredder121.gh_event_api;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public interface TestConstants {

    String DEVELOPER_GITHUB_COM_REVISION = "20bb66a39d77e07e8aee19ef33d039d4cc6618a4";

    /**
     * The HMACs of the payloads as reported by https://www.freeformatter.com/hmac-generator.html
     */
    Map<String, String> HMACS
            = ImmutableMap.<String, String>builder()
            .put("commit_comment",
                    "f8e5ceafd15a2389caacd4ac0f75f73c3217d4c3")
            .put("create",
                    "692767e05058a25010d4aa30f2c5f324350c9a4c")
            .put("delete",
                    "a3cf1acb5d39266a7431ed35e000b3acebbe3634")
            .put("deployment",
                    "dfe1d3fa559e6968587c20397f6339db62d0e360")
            .put("deployment_status",
                    "dec935e7a1d1020ff414e09e38618388983e1a89")
            .put("fork",
                    "d4f7cde1c2a894a96b9f2c651ee4744e31a01a35")
            .put("gollum",
                    "97d92acf4aa127624fab44370aeb50cf7e615bc5")
            .put("issue_comment",
                    "63c8ea0b2d07c8952a0ef86111991070516ce5d1")
            .put("issues",
                    "7eb9a55d933dc1bf5a96e42d5b46a6d71de5562e")
            .put("member",
                    "5662335cff0c69570a1dd2aedc27576c1c20a74b")
            .put("membership",
                    "7cc691ad71931ebe10f59a8aabd572bdfebdc60d")
            .put("page_build",
                    "f1cc7607a6f5e425e960c582fe7dcca3c46ea9f0")
            .put("ping",
                    "ef61d462314dfd950ad5a6e15798bb5432de4e07")
            .put("public",
                    "997a61f879567b839ce00b231bad3764b0289740")
            .put("pull_request",
                    "f699b918351c54176c727329312f24560e5c75f4")
            .put("pull_request_review_comment",
                    "4b63f10a2fff83fa13cc539313c5c9dc1bae42ca")
            .put("push",
                    "0e58dd2372b6d73d905360184beca4ef7654582d")
            .put("release",
                    "6a71ff39e36df0ecc123f823bbb68ed44579dfbd")
            .put("repository",
                    "6aa770b27e558b8a2c09d278cfae46f137927920")
            .put("status",
                    "fd376122b76bbda8e57c81a53d82b91c8389c28b")
            .put("team_add",
                    "8eedb84b5dc45b5494bf8822682f3c1a07e355df")
            .put("watch",
                    "1c9a8fadfb5e7e3b9424839f3852fe180f85f212")
            .build();
}
