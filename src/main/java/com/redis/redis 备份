### save 900 1
    - 当900秒执行 1 个写命令时，启用快照备份
### save 300 10
    - 当300秒执行 10 个写命令时，启用快照备份
### save 60 10000
    - 当 60 秒执行 10000 个写命令时，启用快照备份
#####  Redis 执行 save 命令的时候，将禁止写入命令。
### stop-writes-on-bgsave-error yes
    - bgsave：异步保存命令。系统将启动另外一条进程，把Redis的数据保存到对应的数据文件中。
        他和 save 的最大不同是他不会阻塞客户端的写入，也就是在执行 bgsave 的时候，允许客户端继续 读、写 redis。
    - 在默认情况下
        如果redis执行bgsave失败后，redis 将停止接收写操作。
        这样以一种强硬的方式让用户知道数据不能正确的持久化到磁盘，否则没人会注意到灾难的发生；
        如果后台保存进程重新启动工作了，redis也将自动允许写操作。
        如果安装了可靠的监控，可能不希望redis这样做，那么将其修改为 no。
### rdbchecksum yes
    - 是否对 rdb 文件进行检验。如果对rdb文件检验，从 dbfilename 的配置可以知道，rdb 文件实际是 redis 持久化的数据文件
### dbfilename dump.rdb
    数据文件。当采用快照模式备份（持久化）时。redis 将使用它保存数据，将来可以使用它恢复数据
### appendonly no
    如果 appendonly 配置为 no，则不启用 AOF 方式进行备份。
    如果 appendonly 配置为 yes，则以 AOF 方式进行备份数据，那么此时 redis 会按照配置，在特定的时候执行追加命令，用以备份数据。
### appendfilename "appendonly.aof"
    - 这里定义追加的写入文件为 "appendonly.aof" ，采用AOF追加文件备份的时候都写到这里。
### # appendfsync always
### appendfsync everysec
### # appendfsync no
    - AOF 文件和 redis 命令时同步频率的。
    - 假设配置为 **always** ，当redis执行命令的时候，则同时同步到AOF文件，这样会使得 redis 同步刷新 AOF 文件，造成缓慢。
        每次命令都会持久化，他的优点在于安全，缺点在于每次持久化性能较差。
    - 配置为 everysec ，则代表着每秒同步一次命令到 AOF 文件。
        安全性不如 always ，备份可能会丢失一秒内的命令，但是隐患也不大，安全度尚可，性能可以得到保障。
    - 配置为 no ，则由客户端调用命令执行备份，redis 本身不备份文件。
        性能有所保障，但由于失去备份，所以安全性较差。
    建议采用默认配置 everysec ，这样在保证性能的同时，也一定程度上保证了安全性。
### no-appendfsync-on-rewrite no
    - 指定是否在后台 AOF 文件 rewrite（重写）期间调用 fsync。
    - 默认 no，表示要调用 fsync （无论后台时候有子进程在刷盘）。
    - redis 在后台写 RDB 文件或者重写 AOF 文件期间会存在大量磁盘 I/O ，此时，在某些 Linux 系统中，调用fsync 可能会阻塞。
### auto-aof-rewrite-percentage 100
    - 指定redis 重写 AOF 文件的条件，默认为 ** 100 **，表示与上次 rewrite 的AOF文件大小相比；
    - 若当前 AOF 文件增长量超过上次 AOF 文件大小的 100% 时，就会触发 background rewrite；
    - 若配置为 0 ，则会禁用自动 rewrite。
### auto-aof-rewrite-min-size 64mb
    - 指定触发
### aof-load-truncated yes
    - redis 在恢复时会忽略最后一条可能存在问题的指令，默认为yes。即在AOF写入时，可能存在指令写错的问题（突然断电，写了一半）
    这种情况下 yes 会log并继续，而 no 直接恢复失败。


















